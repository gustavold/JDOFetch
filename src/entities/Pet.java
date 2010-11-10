/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author gustavold
 */
public class Pet {

    private Long id;
    private PetType type;
    private String name;
    private Owner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetType getType() {
        return type;
    }

    public static class PK implements Serializable {

        public Long id;

        public PK() {
        }

        public PK(String s) {
            this.id = Long.valueOf(s);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int hashCode() {
            int hash = 0;
            hash += (long) id;
            return hash;
        }

        public boolean equals(Object object) {
            if (!(object instanceof PK)) {
                return false;
            }
            PK other = (PK) object;
            if (this.id != other.id) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.getClass().getName() + "[id=" + id + "]";
        }
    }
}
