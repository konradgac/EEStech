import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Date;

public class TwitterStreamer {
    Status[] top10 = new Status[11];


    public void stream(final RealTimeGUI gui){
        final Configuration configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("cHkOtFiELcZ7nwloE5Nfq1iDs") // paste your consumer key
                .setOAuthConsumerSecret("9d2ykzFUb3OCO8enoo9Mv0Czxl2yV1zvyKVsjRGEibnpfaMJ4D") //paste your CONSUMER_SECRET
                .setOAuthAccessToken("981949789192507393-Li90Uw21NiPVWKc5HeK8A89ENTe7fzh") //paste your OAUTH_ACCESS_TOKEN
                .setOAuthAccessTokenSecret("Pwe13QzbzbzIjAM3p6TjoXrJwEUbi0vIM5zs6Xc58GzEo") // paste your OAUTH_ACCESS_TOKEN
                .build();

        StatusListener listener = new StatusListener() {

            public void onStatus(Status status) {
                Date time = status.getCreatedAt();
                User user = status.getUser();
                String name = user.getName();
                int followers = user.getFollowersCount();
                Place place = status.getPlace();
                String country = place.getCountry();

                gui.displayTweet(status);
                //System.out.println(status.getText());
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onScrubGeo(long l, long l1) {

            }

            public void onStallWarning(StallWarning stallWarning) {

            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery().track("Donald"));
    }
}
