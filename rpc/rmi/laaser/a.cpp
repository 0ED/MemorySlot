#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

const int strSize = 1000;

int main(int argc,char *argv[])
{
    ifstream infile(argv[1]);
    if(!infile) { cerr<<"infile error!"<<endl };
    ofstream outfile(argv[2]);

    int sequenceNum = 1;
    char str[strSize];
    while(infile){
        infile.getline(str,strSize);
        if(sequenceNum>469)
            str[0] = '2';
        else if(sequenceNum>67)
            str[0] = '4';
        else if(sequenceNum>1)
            str[0] = '1';
        else
            str[0] = '3';
        outfile<<str<<endl;
        ++sequenceNum;
    }
    return 0;
}
