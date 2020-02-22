# COLLECTIONS API 
#### Team members: dmh58, sk593, taj26, wg74, ns217
1. In your experience using these collections, are they hard or easy to use?
    1. They’re generally easy to use. We’ve used them a lot for methods like that update collections like .add(), .remove()
2. In your experience using these collections, do you feel mistakes are easy to avoid?
    1. They’re easy to avoid in the sense that if you do something wrong, exceptions are thrown. Because they’re easily manipulated it’s also easy to make these mistakes (ie trying to remove an element that doesn’t exist, iterating through an empty collection, index out of bounds)
3. How many interfaces do specific concrete collection classes implement (such as LinkedList)? What do you think is the purpose of each interface?
    1. The LinkedList [https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html](LinkedList) implements the following interfaces: Serializable, Cloneable, Iterable, Collection,  Deque, List, Queue. 
The Collections interface allows the linked list to use methods like add, remove, etc. 
    2. Deque [https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html](Deque) and Queue [https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html#offer(E)](Queue) allow it to implement queue methods (like poll, offer, and peek). Iterable allows you to run through the list and implement methods like hasNext. The List interface could be used for sorting the LinkedList. 
Implementing Cloneable interface allow linked list to use the .clone()  method   to make a field-for-field copy of a linked list instance [https://docs.oracle.com/javase/7/docs/api/java/lang/Cloneable.html](Cloneable) . 
    3. The Serializable [https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html](Serializable) interface allows the LinkedList class to be serialized and deserialized. You can convert its state to a byte stream. 
4. How many different implementations are there for a specific collection class (such as Set)? Do you think the number justifies it being an interface or not?
    1. Set has the following 8 implementing classes: AbstractSet, ConcurrentSkipListSet, CopyOnWriteArraySet, EnumSet, HashSet, JobStateReasons, LinkedHashSet, TreeSet.
There are enough implementations that having an interface to contain basic functions makes sense. Different set classes also act differently so with an interface they are able to implement their own functionalities.

5. How many levels of superclasses do specific concrete collection classes have? What do you think is the purpose of each inheritance level?
    1. Specific concrete collection classes have about 4-5 levels of inheritance. For [https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html](LinkedList), there are 4 parents: Object, AbstractCollection, AbstractList, and AbstractSequentialList.
Each inheritance level adds degrees of specificity so that more abstract methods can be defined for a common group of sub-classes/implementations.
6. Why does it make sense to have the utility classes instead of adding that functionality to the collection types themselves? Are there any overlapping methods (ones that are in both a specific collection and a utility class)? If so, is there any guidance on which one you should use?
    1. Having the utility classes allows for encapsulation and hiding implementation from subclasses. There are overlapping methods, but generally speaking it seems that this is only the case when a method should be overridden rather than redefining the method from the class above (i.e. is not the norm).
