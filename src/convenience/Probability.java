/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convenience;

/**
 *
 * @author Ajikozau
 */
public class Probability {
    private final int event;
    private final int probability;
    public int getEvent() { return event; }
    public int getProbability() { return probability; }
    
    public Probability(int event, int probability) {
        this.event = event;
        this.probability = probability;
    }
       
}
