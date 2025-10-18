//211602297 yuval cohen
package collision;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     * @param hl the hl
     * adds the hl as a listener
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}