package co.eci.blacklist.labs.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import co.eci.blacklist.infrastructure.HostBlackListsDataSourceFacade;

public class ParallelBlacklistSearchThread extends Thread {
    private final String ip;
    private final int startServer;
    private final int endServer;
    private final HostBlackListsDataSourceFacade facade;
    private final List<Integer> blackListOccurrences;
    private final AtomicBoolean stop;
    private int totalChecked = 0;
    private int threshold;

    public ParallelBlacklistSearchThread(String ip, int startServer, int endServer, HostBlackListsDataSourceFacade facade, AtomicBoolean stop, int threshold) {
        this.ip = ip;
        this.startServer = startServer;
        this.endServer = endServer;
        this.facade = facade;
        this.blackListOccurrences = new ArrayList<>();
        this.stop = stop; 
        this.threshold=threshold;  
    }

    public List<Integer> getBlackListOccurrences() {
        return blackListOccurrences;
    }

    public int getMatchCount() {
        return blackListOccurrences.size();
    }

    public int getTotalChecked() {
        return totalChecked;
    }
    @Override
    public void run() {
        for (int i = startServer; i < endServer; i++) {
            if (stop.get()) break; 

            if (facade.isInBlackListServer(i, ip)) {
                blackListOccurrences.add(i);
                if (blackListOccurrences.size() >= threshold) {
                    stop.set(true); 
                }
            }
            totalChecked++; 
        }
    }
}
