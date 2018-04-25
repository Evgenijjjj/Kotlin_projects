#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <regex.h>
#include <stdlib.h>

typedef struct catalog_of_letters{
    char name;
    char* path;
}catalog_of_letters;

int cmp(const void* a, const void* b){
    return (*(catalog_of_letters**)a)->name - (*(catalog_of_letters**)b)->name;
}

int bsearch_cmp(const void* a, const void* b){
    //printf("[%c %c]\n", *(char*)a,(*(catalog_of_letters**)b)->name);
    return *(char*)a - (*(catalog_of_letters**)b)->name;
}

void printStruc(catalog_of_letters** s, int count){
    for(int i =0;i<count; i++)
        printf("[%c]\n%s\n", s[i]->name, s[i]->path);
}
int isValid(char *filename){
    char *regexp = "^[a-zA-z].txt$";
    regex_t regexComp;
    
    if(regcomp(&regexComp, regexp, REG_EXTENDED)){
        return 0;
    }
    
    return regexec(&regexComp, filename, 0, NULL, 0) == 0;
}

void getStruct(catalog_of_letters** cl, char* ptr, int count){
    cl[count]->name = ptr[strlen(ptr)-5];
    cl[count]->path = strdup(ptr);
    //printf("%s\n",ptr);
}

void listDir(char *startDir, catalog_of_letters** catalog, int* count){
    char nextDir[200]={0};
    strcpy(nextDir, startDir);
    DIR *dir = opendir(startDir);
    if(!dir)
        return;
    struct dirent *de = readdir(dir);
    while(de){
        if(de->d_type == DT_DIR && strcmp(de->d_name, ".") != 0 &&
           strcmp(de->d_name, "..") != 0){
            int len = strlen(nextDir);
            strcat(nextDir, "/");
            strcat(nextDir,de->d_name);
            listDir(nextDir, catalog, count);
            nextDir[len] = '\0';
        }
        if(de->d_type == DT_REG && isValid(de->d_name)){
            int len = strlen(nextDir);
            strcat(nextDir, "/");
            strcat(nextDir,de->d_name);
            getStruct(catalog, nextDir, *count);
            *count+=1;
            nextDir[len] = '\0';
            
        }
        de = readdir(dir);
    }
    closedir(dir);
    
}
void printWordDir(catalog_of_letters** catalog, char* word, int index, int count,FILE *f){
    if(index > strlen(word)-1)
        return;
    catalog_of_letters** ptr;
    ptr = bsearch(&word[index], catalog, count, sizeof(struct catalog_of_letters*),bsearch_cmp);
    if(ptr){
        fputs((*ptr)->path,f);
        fputs("\n", f);
    }
    
    printWordDir(catalog, word, index+1,count,f);
}


int main(){
    FILE *f = fopen("result.txt","w");
    int count=0;char word[100];
    fgets(word,100,stdin);
    word[strlen(word)-1]='\0';
    
    catalog_of_letters** catalog = (catalog_of_letters**)malloc(66*sizeof(catalog_of_letters*));
    int i;
    for(i =0;i<66;i++){
        catalog[i] = calloc(1,sizeof(catalog));
        
    }
    
    listDir("./tmp", catalog, &count);
    qsort(catalog,count,sizeof(catalog_of_letters*),cmp);
    printWordDir(catalog, word,0,count,f);
    fclose(f);
    

    return 0;
}
