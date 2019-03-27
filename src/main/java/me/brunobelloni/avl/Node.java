package me.brunobelloni.avl;

import java.util.UUID;

class Node {

    UUID key;
    int height;
    Node left, right;

    Node(UUID uuid) {
        this.key = uuid;
        this.height = 1;
    }
}
