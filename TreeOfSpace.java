import java.util.*;

class LockableNode{
    String name;
    LockableNode parent;
    List<LockableNode> children;
    
    int userId;
    boolean locked;
    int lockedChildCount ;
    
    LockableNode(String name){
        this.name = name;
        children = new ArrayList<>();
        userId = -1;
        locked = false;
        lockedChildCount = 0;
    }
    
    public void addChild(LockableNode node){
        node.parent = this;
        children.add(node);
    }
    
    
}



public class Main {

    public static void main(String[] args) {

        /*
                Tree Structure Used:

                        A
                   /    |     \
                  B     C      D
                /   \
               E     F

         */

        LockableNode A = new LockableNode("A");
        LockableNode B = new LockableNode("B");
        LockableNode C = new LockableNode("C");
        LockableNode D = new LockableNode("D");
        LockableNode E = new LockableNode("E");
        LockableNode F = new LockableNode("F");

        A.addChild(B);
        A.addChild(C);
        A.addChild(D);

        B.addChild(E);
        B.addChild(F);

        int user1 = 101;
        int user2 = 202;

        // 🔒 Lock operations
        System.out.println(lock(E, user1));   // true
        System.out.println(lock(F, user1));   // true
        System.out.println(lock(B, user2));   // false (descendants locked)
        System.out.println(" ");   // false (descendants locked)

        // ⬆️ Upgrade lock
        System.out.println(upgradeLock(B, user1)); // true
        System.out.println(B.locked);              // true
        System.out.println(E.locked);              // false
        System.out.println(F.locked);              // false
        System.out.println(" ");   // false (descendants locked)

        // 🔓 Unlock
        System.out.println(unlock(B, user1)); // true
        System.out.println(unlock(B, user2)); // false (wrong user)
    }

    /* ===============================
       IMPLEMENT YOUR METHODS BELOW
       =============================== */

    static boolean lock(LockableNode node, int uid) {
        if(node.locked || node.lockedChildCount > 0 ) {
            return false;
        }
        
        LockableNode curr = node.parent;
        while(curr != null){
            if(curr.locked){
                return false;
            }
            curr = curr.parent; 
        }
        
        curr = node.parent;
        while(curr != null){
            curr.lockedChildCount++;
            curr = curr.parent;
        }
        
        node.locked = true;
        node.userId = uid;
        return true;
        
    }

    static boolean unlock(LockableNode node, int uid) {
        if(!node.locked || node.userId != uid){
            return false;
        }
        
        LockableNode curr = node.parent;
        while(curr != null){
            curr.lockedChildCount--;
            curr = curr.parent;
        }
        
        node.locked = false;
        node.userId = -1;
        
        return true;
        
    }
    
    static void getAllLockedChildren(LockableNode node,List <LockableNode> lockedChildren){
        if(node == null) return;
        
        if (node.locked) {
            lockedChildren.add(node);
        }
            
        for(LockableNode child : node.children){
            getAllLockedChildren(child,lockedChildren);
        }
        
    }

    static boolean upgradeLock(LockableNode node, int uid) {
        if(node.locked || node.lockedChildCount == 0){
            return false;
        }
        
        List <LockableNode> lockedChildren = new ArrayList<>();
        getAllLockedChildren(node,lockedChildren);
        
        for(LockableNode child : lockedChildren){
            if(child.userId != uid){
                return false;
            } 
            unlock(child,uid);
        }
        
        return lock(node,uid);
    }
}
