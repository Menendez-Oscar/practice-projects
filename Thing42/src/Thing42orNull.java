import java.util.Collection;
/**
 * Author: Oscar Menendez
 * Date: 8/23/2014
 */
public interface Thing42orNull<K, D> {

	 /**
     * Add a peer to this object.
     * 
     * @param newPeer
     *            Thing42 object to be added to peers set
     * @throws NullPointerException
     *             - if the specified peer is null
     */
   void addPeer(Thing42orNull<K, D> newPeer);

	 /**
     * Append a member to the pool of this object.
     * 
     * @param newMember
     *            Thing42 object to be appended to pool list
     * @throws NullPointerException
     *             - if the specified item is null
     */
   void appendToPool(Thing42orNull<K, D> newMember);

	/**
     * Access the data of this Thing42.
     * 
     * @return The data of this object
     */
    D getData();

	/**
     * Access the key of this Thing42.
     * 
     * @return the key of this object
     */
    K getKey();

	/**
     * Access the level of this Thing42.
     * 
     * @return the level of this object
     */
    long getLevel();

	/**
     * Access the level of this Thing42.
     * 
     * @return the level of this object
     */
    Thing42orNull<K, D> getOnePeer(K key);

	/**
     * Access a peer matching the specified key.
     * 
     * @param key
     *            the search key
     * @return any peer known by this object that matches the given key; null if
     *         no match
     */
    Collection<Thing42orNull<K, D>> getPeersAsCollection();

	/**
     * Access all peers.
     * 
     * @return all peers known by this object; if no peers then returns a
     *         collection with size() == 0.
     */
    Collection<Thing42orNull<K, D>> getPeersAsCollection(K key);

	/**
     * Access all members of the pool.
     * 
     * @return all members of the pool known by this object; if no members then
     *         returns a list with size() == 0.
     */
    Collection<Thing42orNull<K, D>> getPoolAsList();

	/**
     * Remove a single instance of the specified object from this object's pool.
     * 
     * @param member
     *            the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException
     *             - if the specified parameter is null
     */
    boolean removeFromPool(Thing42orNull<K, D> member);

    /**
     * Remove a single instance of the specified peer from this object.
     * 
     * @param peer - the peer to be removed
     * @return true if a peer was removed as a result of this call
     * @throws NullPointerException - if the specified peer is null
     */
	boolean removePeer(Thing42orNull<K, D> peer);

	/**
	 *  Modify the data of this Thing42.
	 * @param newData
	 */
	void setData(D newData);
}