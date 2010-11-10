/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdofetch;

import dao.ClinicDAO;
import dao.MyDAO;
import entities.Owner;
import entities.Pet;
import java.util.Collection;

/**
 *
 * @author gustavold
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClinicDAO clinic = new MyDAO();
        Collection<Owner> owners = clinic.getOwners();
        for (Owner o : owners) {
            System.out.println("Owner: " + o.getName());
            Collection<Pet> pets = o.getPets();
            for (Pet p : pets) {
                System.out.println("Pet: " + p.getName() + "(" + p.getType().getName() + ")");
            }
        }
    }
}
