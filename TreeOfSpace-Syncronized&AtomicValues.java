import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class LockableNode {
    String name;
    LockableNode parent;
    List<LockableNode> children;

    int userId;
    boolean locked;
    AtomicInteger lockedChildCount;

    LockableNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.userId = -1;
        this.locked = false;
        this.lockedChildCount = new AtomicInteger(0);
    }

    public synchronized void addChild(LockableNode node) {
        node.parent = this;
        children.add(node);
    }
}

public class Main {

    /* ===============================
       THREAD SAFE LOCK
       =============================== */
    static synchronized boolean lock(LockableNode node, int uid) {

        if (node.locked || node.lockedChildCount.get() > 0) {
            return false;
        }

        LockableNode curr = node.parent;
        while (curr != null) {
            synchronized (curr) {
                if (curr.locked) {
                    return false;
                }
            }
            curr = curr.parent;
        }

        curr = node.parent;
        while (curr != null) {
            curr.lockedChildCount.incrementAndGet();
            curr = curr.parent;
        }

        node.locked = true;
        node.userId = uid;
        return true;
    }

    /* ===============================
       THREAD SAFE UNLOCK
       =============================== */
    static synchronized boolean unlock(LockableNode node, int uid) {

        if (!node.locked || node.userId != uid) {
            return false;
        }

        LockableNode curr = node.parent;
        while (curr != null) {
            curr.lockedChildCount.decrementAndGet();
            curr = curr.parent;
        }

        node.locked = false;
        node.userId = -1;
        return true;
    }

    /* ===============================
       DFS TO COLLECT LOCKED CHILDREN
       =============================== */
    static void getAllLockedChildren(
            LockableNode node,
            List<LockableNode> lockedChildren) {

        if (node == null) return;

        synchronized (node) {
            if (node.locked) {
                lockedChildren.add(node);
            }
        }

        for (LockableNode child : node.children) {
            getAllLockedChildren(child, lockedChildren);
        }
    }

    /* ===============================
       THREAD SAFE UPGRADE LOCK
       =============================== */
    static synchronized boolean upgradeLock(LockableNode node, int uid) {

        if (node.locked || node.lockedChildCount.get() == 0) {
            return false;
        }

        List<LockableNode> lockedChildren = new ArrayList<>();
        getAllLockedChildren(node, lockedChildren);

        for (LockableNode child : lockedChildren) {
            if (child.userId != uid) {
                return false;
            }
        }

        for (LockableNode child : lockedChildren) {
            unlock(child, uid);
        }

        return lock(node, uid);
    }
}
