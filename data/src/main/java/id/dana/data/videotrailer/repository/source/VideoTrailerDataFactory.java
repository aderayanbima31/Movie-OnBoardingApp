package id.dana.data.videotrailer.repository.source;

import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.repository.source.mock.MockVideoTrailerEntityData;
import id.dana.data.videotrailer.repository.source.network.NetworkVideoTrailerEntityData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VideoTrailerDataFactory {

    private final MockVideoTrailerEntityData entityDataMock;

    private final NetworkVideoTrailerEntityData entityDataNetwork;

    @Inject
    public VideoTrailerDataFactory(
        MockVideoTrailerEntityData entityDataMock,
        NetworkVideoTrailerEntityData entityDataNetwork) {
        this.entityDataMock = entityDataMock;
        this.entityDataNetwork = entityDataNetwork;
    }

    public VideoTrailerEntityData createData(String source) {
        switch (source) {
            case "Mock":
                return entityDataMock;
            case "Network":
                return entityDataNetwork;
            default:
                return null;
        }
    }
}
