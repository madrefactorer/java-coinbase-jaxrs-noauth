public interface Subject {

    void registerSubscriber(Subscriber subscriber, boolean watchPriceUp, boolean watchPriceDown);
    //void removeSubscriber(Subscriber subscriber);
    void notifySubscribers();
}
