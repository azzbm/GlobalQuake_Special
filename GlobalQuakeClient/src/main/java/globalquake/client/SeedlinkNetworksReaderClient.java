package globalquake.client;

import globalquake.core.SeedlinkNetworksReader;

public class SeedlinkNetworksReaderClient extends SeedlinkNetworksReader {

    @Override
    public long getLastReceivedRecordTime() {
        return System.currentTimeMillis(); // TODO better?
    }
}
