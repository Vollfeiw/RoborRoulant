//Matthieu VION 
// Groupe J2

public class RobotRoulant
{
    protected static final int NB_LIGNE   = 8;
    protected static final int NB_COLONNE = 8;

    protected static int  nbInstance=0;
    
    protected int    toxicEstNul;
    protected int    numRobot;
    protected int    ligne;
    protected int    colonne;
    protected String couleur;

    protected int    jauge;
    protected int    ligneOrig;
    protected int    colonneOrig;


    public RobotRoulant ( int ligne, int colonne, String couleur )
    {
        this.numRobot= ++nbInstance;

        if (ligne  <0            ) ligne   = 0;
        if (ligne  >NB_LIGNE-1   ) ligne   = NB_LIGNE-1;
        if (colonne<0            ) colonne = 0;
        if (colonne>NB_COLONNE-1 ) colonne = NB_COLONNE-1;

        this.ligne       = ligne;
        this.colonne     = colonne;
        this.couleur     = couleur;

        this.jauge       = 100;
        this.ligneOrig   = ligne;
        this.colonneOrig = colonne;
    }

    public String toString()
    {
        return getType() + String.format("%2d",   numRobot ) + " "  +
                           String.format("%-10s", couleur  ) + "("  +
                           String.format("%2d",   ligne    ) + ":"  +
                           String.format("%2d",   colonne  ) + ") " +
                           " energie " + jauge;
    }

    private String getType() {return "RobotR "; }

    public int getLigne  (){ return ligne;   }
    public int getColonne(){ return colonne; }


    public boolean deplacer ( String dir, RobotRoulant[] tabRobot, int nbRobot)
    {
        if (jauge == 0) return false;
        int nvLigne   = ligne   + deltaLigne   (dir);
        int nvColonne = colonne + deltaColonne (dir);

        if ( nvLigne   < 0 || nvLigne   >= NB_LIGNE   ) return false;
        if ( nvColonne < 0 || nvColonne >= NB_COLONNE ) return false;

        for ( int cpt=0;cpt<nbRobot;cpt++ )
            if ( tabRobot[cpt].ligne == nvLigne && tabRobot[cpt].colonne == nvColonne ) return false;

        this.ligne   = nvLigne;
        this.colonne = nvColonne;

        if ( this.ligne == this.ligneOrig && this.colonne == this.colonneOrig )  this.jauge  = 100;
        else                                                                     this.jauge -=   2;

        return true;

    }

    protected int deltaLigne ( String dir )
    {
        if ( dir.indexOf ( "N" ) != -1 ) return -1;
        if ( dir.indexOf ( "S" ) != -1 ) return  1;

        return 0;
    }

    protected int deltaColonne ( String dir )
    {
        if ( dir.indexOf ( "O" ) != -1 ) return -1;
        if ( dir.indexOf ( "E" ) != -1 ) return  1;

        return 0;
    }
}



