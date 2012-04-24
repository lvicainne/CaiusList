package caius.caiuslist;

public class Person {
	private String nom = null;
	private boolean selected = false;

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean isSelected() {
        return selected;
    }
	
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
	
    public void switchSelected() {
        this.selected = !this.selected;
    }
	
    /**
     * Est-ce que le contact répond aux critères d'intégrité ?
     * @return true si le contact est valide
     */
    public boolean isCorrect() {
        return (this.nom != null && this.nom.compareTo("") != 0);
    }
	
    /**
     * Fonction de hashage, génère un code unique pour chaque contact
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        return result;
    }

    /**
     * Permet de savoir si deux contacts sont égaux
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Person))
            return false;

        Person other = (Person) obj;

        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;

        return true;
    }
}