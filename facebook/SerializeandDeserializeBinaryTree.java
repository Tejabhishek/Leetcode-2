public class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.substring(0, sb.length() - 1);
    }
    
    private void buildString(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append("#,")
        } else {
            sb.append(node.val + ",");
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) retur null;
        String[] nodeStrs = data.split(",");
        int[] index = {0};
        return buildTree(nodeStrs, index);
    }
    private TreeNode buildTree(String[] nodeStrs, int[] index) {
        TreeNode node = null;
        if(index[0] > nodeStrs.length) return node;
        String nodeStr = nodeStrs[index[0]];
        if(!"#".equals(nodeStr)) {
            node = new TreeNode(Integer.parseInt(nodeStr));
            index[0]++;
            node.left = buildTree(nodeStrs, index);
            index[0]++;
            node.right = buildTree(nodeStrs, index);
        }
        return node;
    }
}


public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
