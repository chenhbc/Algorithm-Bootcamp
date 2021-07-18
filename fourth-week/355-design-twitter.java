class Twitter {

    //封装了一个 User 结构，以免 userId 存放在推文和关注列表两个集合里，节省空间
    private HashMap<Integer, User> users;

    // 发送推文时间，用于合并两个链表时判断时间先后顺序
    private HashMap<Integer, Integer> tweetTimes;

    // 发推时间
    private int time = 0;

    // 推文条数
    private int tweetMaxCount = 10;

    /** Initialize your data structure here. */
    public Twitter() {
        users = new HashMap<>();
        tweetTimes = new HashMap<>();
    }

    private User getUser(int userId) {
        User user = users.get(userId);
        if (null == user) {
            user = new User();
            users.put(userId, user);
        }
        return user;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = getUser(userId);
        // 添加到链表头部
        user.tweets.offerFirst(tweetId);
        // 移除多余的推文，只需要记录最大10条即可
        if (user.tweets.size() > tweetMaxCount) {
            user.tweets.removeLast();
        }
        // 记录时间
        tweetTimes.put(tweetId, ++time);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User user = getUser(userId);
        List<LinkedList<Integer>> allTweets = new ArrayList<>();
        
        // 自己的推文
        allTweets.add(user.tweets);

        // 关注者的推文
        for (Integer followeeId : user.follows) {
            if (users.containsKey(followeeId)) {
                allTweets.add(users.get(followeeId).tweets);
            }
        }

        // 合并 N 个有序链表
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer int1, Integer int2) {
                return tweetTimes.get(int2) - tweetTimes.get(int1);
            }
        });
        
        for (LinkedList<Integer> linkedList : allTweets) {
            for (Integer id : linkedList) {
                queue.add(id);
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        int i = tweetMaxCount;
        while(!queue.isEmpty()) {
            if (i <= 0) {
                break;
            }
            ans.add(queue.poll());
            i--;
        }
        return ans;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        // 防止自己关注自己
        if (followerId == followeeId) {
            return;
        }
        User user = getUser(followerId);
        if (!user.follows.contains(followeeId)) {
            user.follows.add(followeeId);
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User user = getUser(followerId);
        user.follows.remove(followeeId);
    }
}

/**
* 需要维护两个东西：一个是用户发的推文，一个是用户的关注列表
* 因为推文只需要最近的十条推文，所以维护一个哈希表+链表，用来按照时间线存储
* 用户列表不需要展示，只用一个 Set 来维护关系即可。
* 展示推文的时候，先获取用户的关注列表，然后合并这个链表并取前 10 即可。
*/
class User {
    // 用户的关注列表
    HashSet<Integer> follows;

    // 推文
    LinkedList<Integer> tweets;

    User() {
        follows = new HashSet<>();
        tweets = new LinkedList<>();
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
