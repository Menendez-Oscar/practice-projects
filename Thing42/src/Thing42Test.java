

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Thing42Test.
 *
 * @author  Oscar Menendez
 * @version 8/23/2014
 */
public class Thing42Test
{

    
    Thing42orNull thing;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        //first Thing42 object meant for test use
        thing = new Thing42("k1", 1, "thing test data");

    }

    @Test
    public void addPeerTest(){

        Thing42orNull empty = null; 
        boolean thrown = false;
        try{
            thing.addPeer(empty);
        }
        catch(NullPointerException e){
            thrown = true;
        }
        assertTrue(thrown); 
        thrown = false;
        Thing42orNull newThing = new Thing42("k12", 2, "peer test data1"); 
        try{
            thing.addPeer(newThing);
        }
        catch(NullPointerException e){
            thrown = true;
        }              
        assertFalse(thrown);       
    }
    
    @Test
    public void appendToPoolTest()
    {
        Thing42orNull empty = null; 
        boolean thrown = false;
        try{
            thing.appendToPool(empty);
        }
        catch(NullPointerException e){
            thrown = true;
        }
        assertTrue(thrown); 
        thrown = false;
        Thing42orNull newThing = new Thing42("k13", 2, "pool test data"); 
        try{
            thing.appendToPool(newThing);
        }
        catch(NullPointerException e){
            thrown = true;
        }              
        assertFalse(thrown);      
    }
    
    @Test
    public void gettersTest(){
        assertEquals(thing.getKey(),"k1");
        assertEquals(thing.getLevel(), 1);
        assertEquals(thing.getData(), "thing test data");
        Thing42orNull newThing = new Thing42("k12", 2, "peer test data"); 
        thing.addPeer(newThing);
        thing.appendToPool(newThing);
        assertEquals(thing.getOnePeer("k12"), newThing);
        assertEquals(thing.getPeersAsCollection().size(), 1);//check number of peers
        Thing42orNull newThing2 = new Thing42("k13", 2, "peer 2 test data"); 
        thing.addPeer(newThing2);
        thing.appendToPool(newThing2);
        assertEquals(thing.getPeersAsCollection("k12").size(), 1);
        Thing42orNull newThing3 = new Thing42("k12", 2, "peer 3 test data"); 
        thing.addPeer(newThing3);
        thing.appendToPool(newThing3);
        assertEquals(thing.getPeersAsCollection("k12").size(), 2);        
        assertTrue(thing.getPeersAsCollection("k13").contains(newThing2));
        assertEquals(thing.getPeersAsCollection().size(), 3);
                
        assertEquals(thing.getPoolAsList().size(), 3);
    }
    
    @Test
    public void removersTest(){
                
        Thing42orNull newThing3 = new Thing42("k12", 2, "peer 3 test data"); 
        thing.addPeer(newThing3);
        thing.appendToPool(newThing3);
        Thing42orNull newThing2 = new Thing42("k13", 2, "peer 2 test data"); 
        thing.addPeer(newThing2);
        thing.appendToPool(newThing2);
        Thing42orNull newThing = new Thing42("k12", 2, "peer test data"); 
        thing.addPeer(newThing);
        thing.appendToPool(newThing);
        
        boolean thrown = false;
        
        try{
            thing.removeFromPool(null);
        }
        catch(NullPointerException e){
            thrown = true;
        }
        assertTrue(thrown); 
        thrown = false;
        try{
            thing.removeFromPool(newThing);
            thing.removePeer(newThing);
            thing.removePeer(newThing3);
        }
        catch(NullPointerException e){
            thrown = true;
        }              
        assertFalse(thrown);  
        assertEquals(thing.getPoolAsList().size(), 2);
        assertFalse(thing.getPoolAsList().contains(newThing));
        assertTrue(thing.getPoolAsList().contains(newThing2)
                    && thing.getPoolAsList().contains(newThing3));
        assertEquals(thing.getPeersAsCollection().size(), 1);
        assertFalse(thing.getPoolAsList().contains(newThing));
        assertTrue(thing.getPoolAsList().contains(newThing2));
    }
    
    
    @Test
    public void setDataTest(){
        Thing42orNull newThing3 = new Thing42("k12", 2, "peer 3 test data"); 
        Thing42orNull newThing2 = new Thing42("k13", 2, "peer 2 test data"); 
        
        thing.setData("something else");
        assertEquals(thing.getData(), "something else");
        
        newThing2.setData(newThing3.getData());
        assertEquals(newThing2.getData(), newThing3.getData());     
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }


}

