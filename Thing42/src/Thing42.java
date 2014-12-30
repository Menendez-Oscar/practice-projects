import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Author: Oscar Menendez
 * Date: 8/23/2014
 */
public class Thing42<K, D> implements Thing42orNull<K, D> {
    /**
     * Immutable generic key
     */
    K key;
    /**
     * Immutable generic level
     */
    long level;
    /**
     * generic mutable attribute data
     */
    D data;
    /**
     * ordered collection of Thing42 objects pool.
     */
    Collection<Thing42orNull<K, D>> pool;
    /**
     * unordered collection of Thing42 objects peers
     */
    Collection<Thing42orNull<K, D>> peers;

    /**
     * Thing42 constructor
     * 
     * @param key
     * @param level
     * @param data
     */
    public Thing42(K key, long level, D data) {
        this.key = key;
        this.level = level;
        this.data = data;
        pool = new ArrayList<>();
        peers = new HashSet<>();
    }

    /**
     * Add a peer to this object.
     * 
     * @param newPeer
     *            Thing42 object to be added to peers set
     * @throws NullPointerException
     *             - if the specified peer is null
     */
    @Override
    public void addPeer(Thing42orNull<K, D> newPeer) {
        if (newPeer == null)
            throw new NullPointerException();
        else
            peers.add(newPeer);
    }

    /**
     * Append a member to the pool of this object.
     * 
     * @param newMember
     *            Thing42 object to be appended to pool list
     * @throws NullPointerException
     *             - if the specified item is null
     */
    @Override
    public void appendToPool(Thing42orNull<K, D> newMember) {
        if (newMember == null)
            throw new NullPointerException();
        else
            pool.add(newMember);
    }

    /**
     * Access the data of this Thing42.
     * 
     * @return The data of this object
     */
    @Override
    public D getData() {
        return data;
    }

    /**
     * Access the key of this Thing42.
     * 
     * @return the key of this object
     */
    @Override
    public K getKey() {
        return key;
    }

    /**
     * Access the level of this Thing42.
     * 
     * @return the level of this object
     */
    @Override
    public long getLevel() {
        return level;
    }

    /**
     * Access a peer matching the specified key.
     * 
     * @param key
     *            the search key
     * @return any peer known by this object that matches the given key; null if
     *         no match
     */
    @Override
    public Thing42orNull<K, D> getOnePeer(K key) {

        for (Thing42orNull<K, D> peer : peers) {
            if (peer.getKey().equals(key))
                return peer;
        }
        return null;
    }

    /**
     * Access all peers.
     * 
     * @return all peers known by this object; if no peers then returns a
     *         collection with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection() {
        return peers;
    }

    /**
     * Access all peers matching the specified key.
     * 
     * @param key
     *            the search key
     * @return all peers known by this object that match the given key; if no
     *         peer matches then returns a collection with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection(K key) {

        Collection<Thing42orNull<K, D>> col = new HashSet<>();

        for (Thing42orNull<K, D> peer : peers) {
            if (peer.getKey().equals(key)){
                col.add(peer);
            }
        }
        return col;
    }

    /**
     * Access all members of the pool.
     * 
     * @return all members of the pool known by this object; if no members then
     *         returns a list with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPoolAsList() {
        return pool;
    }

    /**
     * Remove a single instance of the specified object from this object's pool.
     * 
     * @param member
     *            the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException
     *             - if the specified parameter is null
     */
    @Override
    public boolean removeFromPool(Thing42orNull<K, D> member) {
        if (member == null)
            throw new NullPointerException();
        return pool.remove(member);
    }

    /**
     * Remove a single instance of the specified peer from this object.
     * 
     * @param peer - the peer to be removed
     * @return true if a peer was removed as a result of this call
     * @throws NullPointerException - if the specified peer is null
     */
    @Override
    public boolean removePeer(Thing42orNull<K, D> peer) {
        if (peer == null)
            throw new NullPointerException();
        return peers.remove(peer);
    }

    /**
     * Modify the data of this Thing42.
     * 
     * @param newData
     *            the updated data for this object
     */
	@Override
	public void setData(D newData) {
		this.data = newData;
	}

}