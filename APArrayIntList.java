public class APArrayIntList implements APIntList {
    private int[] list;
    private int index;

    public APArrayIntList() {
        this.list = new int[5];
        this.index = 1;
    }

    /**
     * Returns the size of the list
     */
    public int size() {
        return this.index;
    }

    /**
     * Adds the item to the end of the list
     * @param item
     */
    public void add(int item) {
        resizeIfNeeded();
        this.list[this.index - 1] = item;
        index++;
    }

    /**
     * Add item to the specified index, move everything else up
     * @param index
     * @param item
     */
    public void add(int index, int item) {
        if (index < 0 || index >= this.size() + 1) {
            throw new IndexOutOfBoundsException();
        }
        resizeIfNeeded();
        System.arraycopy(this.list, index, this.list, index + 1, this.index - index);
        this.list[index] = item;
        index++;
    }

    /**
     * Replaces element at index with item
     * @param index
     * @param item
     */
    public void set(int index, int item) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.list[index] = item;
    }

    /**
     * Gets item at specified index
     * @param index
     * @return int at index
     */
    public int get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.list[index];
    }

    /**
     * Checks if item exists in list
     * @param item
     * @return If the item exists
     */
    public boolean contains(int item) {
        for (int i = 0; i < this.list.length - 1; i++)
            if (this.list[i] == item)
                return true;
                
        return false;
    }

    /**
     * Removes the index at the specified index
     * @param index
     * @return the item that was removed from the list
     */
    public int remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        int itemToMove = this.list[index];
        int moved = this.index - index - 1;
        if (moved > 0) {
            System.arraycopy(this.list, index + 1, this.list, index, moved);
        }
        this.list[--this.index] = 0;
        
        return itemToMove;
    }

    /**
     * Nulls out all values and trims list down to 0
     * <b>This method is destructive</b>
     */
    public void clear() {
        this.list = new int[1];
        this.index = 0;
    }
    
    /**
     * Resizes the underlying array if it's about to overflow.
     */
    private void resizeIfNeeded() {
        if (this.size() + 1 > this.list.length) {
            resize();
        }
    }
    
    /// Rebuild and increment list size
    private void resize() {
        int[] newList = new int[this.list.length * 2];
        for (int i = 0; i < this.list.length - 1; i++) {
            newList[i] = this.list[i];
        }
        this.list = newList;
    }
}
