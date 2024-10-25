package ObserverPatternTaskOne;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Channel sleepingKittens = new Channel("Sleeping Kittens");
        Channel staringDogs = new Channel("Staring Dogs");

        UserAccount user1 = new UserAccount("Adam");
        UserAccount user2 = new UserAccount("Bob");
        UserAccount user3 = new UserAccount("Eve");

        // Users subscribe to channels
        sleepingKittens.addObserver(user1);
        staringDogs.addObserver(user2);
        sleepingKittens.addObserver(user3);
        staringDogs.addObserver(user3);

        // Publish new videos
        sleepingKittens.addVideo("vid123");
        staringDogs.addVideo("vid456");

        // Check notifications
        System.out.println(user1.getUsername() + "'s notifications: " + user1.getVideoNotifications());
        System.out.println(user2.getUsername() + "'s notifications: " + user2.getVideoNotifications());
        System.out.println(user3.getUsername() + "'s notifications: " + user3.getVideoNotifications());
    }
}

class Video {
    private final String id;

    public Video(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

interface Observer {
    void updatePush(Video video);  // Push-based notification
    void updatePull(Channel channel);  // Pull-based notification
}

interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserversPush(Video video);
    void notifyObserversPull();
}

class Channel implements Observable {
    private final String name;
    private final List<Observer> subscribers = new ArrayList<>();
    private final List<Video> videos = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Video> getVideos() {
        return new ArrayList<>(videos);
    }

    public void addVideo(String videoId) {
        Video newVideo = new Video(videoId);
        videos.add(newVideo);
        notifyObserversPush(newVideo);  // Notify subscribers with push
//        notifyObserversPull();  // Notify subscribers with pull
    }

    @Override
    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObserversPush(Video video) {
        for (Observer subscriber : subscribers) {
            subscriber.updatePush(video);
        }
    }

    @Override
    public void notifyObserversPull() {
        for (Observer subscriber : subscribers) {
            subscriber.updatePull(this);
        }
    }
}
class UserAccount implements Observer {
    private final String username;
    private final List<String> videoNotifications = new ArrayList<>();

    public UserAccount(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getVideoNotifications() {
        return new ArrayList<>(videoNotifications);
    }

    @Override
    public void updatePush(Video video) {
        videoNotifications.add(video.getId());
        System.out.println(username + " received push notification: New video " + video.getId());
    }

    @Override
    public void updatePull(Channel channel) {
        List<Video> videos = channel.getVideos();
        if (!videos.isEmpty()) {
            String latestVideoId = videos.get(videos.size() - 1).getId();
            videoNotifications.add(latestVideoId);
            System.out.println(username + " received pull notification: Latest video from " + channel.getName() + " is " + latestVideoId);
        }
    }
}


