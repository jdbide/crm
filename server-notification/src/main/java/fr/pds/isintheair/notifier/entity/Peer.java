package fr.pds.isintheair.notifier.entity;

public class Peer<L, R> {

    public L tablet;
    public R phone;

    @Override
    public int hashCode () {
        return tablet.hashCode() ^ phone.hashCode();
    }
}