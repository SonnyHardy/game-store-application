package com.sonny.store.game;

import com.sonny.store.category.Category;
import com.sonny.store.comment.Comment;
import com.sonny.store.common.BaseEntity;
import com.sonny.store.platform.Console;
import com.sonny.store.platform.Platform;
import com.sonny.store.whishlist.Wishlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Game extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Platform> platforms;

    private String coverPicture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "game", orphanRemoval = true)  // orphanRemoval = true means that if we delete a game, all its comments will be removed too
    private List<Comment> comments;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_wishlist",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "wishlist_id")
            }
    )
    private List<Wishlist> wishlists;

    public void addWishlist(Wishlist wishlist) {
        this.wishlists.add(wishlist);
        wishlist.getGames().add(this);
    }

    public void removeWishlist(Wishlist wishlist) {
        this.wishlists.remove(wishlist);
        wishlist.getGames().remove(this);
    }

    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
        platform.getGames().add(this);
    }

    public void removePlatform(Platform platform) {
        this.platforms.remove(platform);
        platform.getGames().remove(this);
    }
}
