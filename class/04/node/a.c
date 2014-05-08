#include <stdio.h>
#define N 100
struct cell {
    int node;
    struct cell *next;
};

void preorder(int k, struct cell **S);


int main(void) {
    struct cell *S[N];
    int root;
    printf("preorder=");
    preorder(root, S);
    printf("\n");
}

void preorder(int k, struct cell **S) {
    struct cell *q;
    printf(" %d", k);
    q = S[k];
    while (q != NULL) {
        preorder(q->node, S);
        q = q -> next;
    }
    return;
}