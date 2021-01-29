package melnikov.qualification.auxiliary;

import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MeetingCreator {
    private Party party;
    private ArrayList<Interval> createdMeetings;

    public MeetingCreator(Party party) {
        this.party = party;
        createdMeetings=new ArrayList<>();
    }

    public void createMeetings(){
        TreeSet<Interval> sortedInterval=new TreeSet<>(party.getMeetings());
        Set<Player> required = party.getPlayers();
        Interval start = sortedInterval.pollFirst();
        while (!sortedInterval.isEmpty()) {
            Interval first = sortedInterval.pollFirst();
            while (first.getAvailableOnes() != required && !sortedInterval.isEmpty()) {
                first.intersect(sortedInterval.pollFirst());
            }
            Interval goodmiting = first;
            if (goodmiting.getAvailableOnes().equals(required)) {
                createdMeetings.add(goodmiting);
            }
        }
    }

    public void createMeetings2(){
        TreeSet<Interval> sortedInterval=new TreeSet<>(party.getMeetings());
        ArrayList<Interval> arLInOrder = new ArrayList<>();
        Set<Player> required = party.getPlayers();
        while (!sortedInterval.isEmpty()){
            arLInOrder.add(sortedInterval.pollFirst());
        }
        for (int i = 0; i < arLInOrder.size(); i++) {
            Interval first = arLInOrder.get(i);
            int next=i+1;
            while (first.getAvailableOnes()!=required&&next<arLInOrder.size()){
               first.intersect(arLInOrder.get(next));
               next++;
                if (first.getAvailableOnes().equals(required)) createdMeetings.add(first);
            }
        }
    }

    public ArrayList<Interval> getCreatedMeetings() {
        return createdMeetings;
    }
}
