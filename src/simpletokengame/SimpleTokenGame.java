/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simpletokengame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xsltnet.DecisionListener;
import xsltnet.Edge;
import xsltnet.Net;
import xsltnet.NetLogic;
import xsltnet.Place;
import xsltnet.Transition;
import static xsltnet.XMLUtils.printNet;


/**
 *
 * @author Heiko
 */
public class SimpleTokenGame {
    
    public static void generateNet(Net net) throws IOException {
        Place s1 = new Place();
        s1.addDocumentPath("q1.xml", net);
        s1.addDocumentPath("q1b.xml", net);
        s1.setDownSchemaURL(null);
        s1.setUpSchemaURL("q1.xsd");
        s1.setId("s1");
        net.getPlaces().add(s1);
        
        Place s2 = new Place();
        s2.addDocumentPath("q2.xml", net);
        s2.setDownSchemaURL(null);
        s2.setUpSchemaURL("q2.xsd");
        s2.setId("s2");
        net.getPlaces().add(s2);
        
        Place s3 = new Place();
        s3.addDocumentPath("q3.xml", net);
        s3.setDownSchemaURL(null);
        s3.setUpSchemaURL("q3.xsd");
        s3.setId("s3");
        net.getPlaces().add(s3);
        
        Place s4 = new Place();
        //s4.addDocumentPath("e5.xml" ,net);
        s4.setDownSchemaURL(null);
        s4.setUpSchemaURL(null);
        s4.setId("s4");
        net.getPlaces().add(s4);
        
        Place s5 = new Place();
        //s4.addDocumentPath("e5.xml" ,net);
        s5.setDownSchemaURL(null);
        s5.setUpSchemaURL(null);
        s5.setId("s5");
        net.getPlaces().add(s5);
        
        Transition t1 = new Transition();
        t1.setTransitionsischrift("//value[@name='withBreakfast']/text()=\"true\" ");
        //t1.setTransitionsischrift(null);
        t1.setId("t1");
        net.getTransitions().add(t1);
        
        Transition t2 = new Transition();   
        t2.setTransitionsischrift("//value[@name='roomPrice']/text()>=100");
        t2.setId("t2");
        net.getTransitions().add(t2);
        
        Transition t3 = new Transition();   
        t3.setTransitionsischrift(null);
        t3.setId("t3");
        net.getTransitions().add(t3);
        
        Edge e1 = new Edge();
        e1.setIncomingEdge(true);
        e1.setPlace(s1);
        e1.setTransition(t1);
        e1.setXinschriftURL("x1.xslt");
        e1.setId("e1");
        net.getEdges().add(e1);
        
        Edge e2 = new Edge();
        e2.setIncomingEdge(true);
        e2.setPlace(s2);
        e2.setTransition(t1);
        e2.setXinschriftURL("x2.xslt");
        e2.setId("e2");
        net.getEdges().add(e2);
        
        Edge e3 = new Edge();
        e3.setIncomingEdge(false);
        e3.setPlace(s3);
        e3.setTransition(t1);
        e3.setXinschriftURL("x3.xslt");
        e3.setId("e3");
        net.getEdges().add(e3);
        
        Edge e4 = new Edge();
        e4.setIncomingEdge(true);
        e4.setPlace(s3);
        e4.setTransition(t2);
        e4.setXinschriftURL("x4.xslt");
        e4.setId("e4");
        net.getEdges().add(e4);
        
        Edge e5 = new Edge();
        e5.setIncomingEdge(false);
        e5.setPlace(s4);
        e5.setTransition(t2);
        e5.setXinschriftURL("x5.xslt");
        e5.setId("e4");
        net.getEdges().add(e5);
        
        Edge e6 = new Edge();
        e6.setIncomingEdge(true);
        e6.setPlace(s3);
        e6.setTransition(t3);
        e6.setXinschriftURL("x4.xslt");
        e6.setId("e6");
        net.getEdges().add(e6);
        
        Edge e7 = new Edge();
        e7.setIncomingEdge(false);
        e7.setPlace(s5);
        e7.setTransition(t3);
        e7.setXinschriftURL("x5.xslt");
        e7.setId("e7");
        net.getEdges().add(e7);
        

    }
    
  
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        System.out.println("### Ausgangsituation ######################################################################################################################################\n" +
            "\n" +
            "\n" +
            "       E1    ____________                        ____________              \n" +
            "(S1)-------->|          |                        |          |              \n" +
            "             |          |    E3           E4     |          |    E5        \n" +
            "             |    T1    |--------->(S3)--------->|    T2    |--------->(S4)\n" +
            "       E2    |          |            |           |          |              \n" +
            "(S2)-------->|__________|            |           |__________|              \n" +
            "                                     |                                     \n" +
            "                                     |           ____________              \n" +
            "                                     |           |          |              \n" +
            "                                     |   E6      |          |    E7        \n" +
            "                                     |---------->|    T3    |--------->(S5)\n" +   
            "                                                 |          |              \n" +   
            "                                                 |__________|              \n" +
            "                                                                           \n" +
                   "\n");
        Net net = new Net("C:/Users/Heiko/Desktop/ma_test1/");
        try {
            
            generateNet(net);
            System.out.println(printNet(net, true, true, true));
            //NetLogic.simulateWithSequentialStrategy(net);
            NetLogic.startTokenGame(net, new DecisionListener() {

                @Override
                public Transition onActiveTransactions(List<Transition> transitions) {
                    System.out.println("\n\n### Welche Transition soll geschalten werden? #############################################################################################################\n");
                    int i = 0;
                    for(Transition t: transitions) {
                        System.out.println("["+i+"] "+ t.getId());
                        i++;
                    }
                    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    System.out.print("Transitionsnummer eingeben: ");
                    String choice = "0";
                    try {
                        choice =  reader.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(SimpleTokenGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    return transitions.get(new Integer(choice));
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        } catch (Exception ex) {
            Logger.getLogger(SimpleTokenGame.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
