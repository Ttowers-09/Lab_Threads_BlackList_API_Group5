package co.eci.blacklist.labs.part2;

import co.eci.blacklist.infrastructure.HostBlackListsDataSourceFacade;
import java.util.List;
import java.util.ArrayList;

public class ParallelBlacklistSearchThread extends Thread {
    private final String ip;
    private final int startServer;
    private final int endServer;
    private final HostBlackListsDataSourceFacade facade;
    private final List<Integer> blackListOccurrences;

    public ParallelBlacklistSearchThread(String ip, int startServer, int endServer, HostBlackListsDataSourceFacade facade) {
        this.ip = ip;
        this.startServer = startServer;
        this.endServer = endServer;
        this.facade = facade;
        this.blackListOccurrences = new ArrayList<>();
    }

    public List<Integer> getBlackListOccurrences() {
        return blackListOccurrences;
    }

    public int getMatchCount() {
        return blackListOccurrences.size();
    }

    @Override
    public void run() {
        for (int i = startServer; i <= endServer; i++) {
            if (facade.isInBlackListServer(i, ip)) {
                blackListOccurrences.add(i);
            }
        }
    }
}
