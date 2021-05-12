#ifndef BANQUE_H
#define BANQUE_H
#include "compte.h"

class Banque
{
    char Nom[20];
    Compte * Liste[1000] ;
    int pos ;
    Compte* Existe(int Num); // renvoie true si le compte Num existe false sinon
public:
    Banque(char*);
    void Add(char* , float );
    void Add(Compte *);
    void Afficher();
    bool Virer(int NumSrc , int NumDest , float Mnt);
};

#endif // BANQUE_H
