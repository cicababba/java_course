package model;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR_ARTICLES")
public class AuthorArticlesEntity {
    // TODO questa tabella é opzionale, non é necessario mapparla ammenoche non vogliamo aggiungere altri campi alla tabella

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity articleEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public void setArticleEntity(ArticleEntity articleEntity) {
        this.articleEntity = articleEntity;
    }
}

// todo utente (id, username, email) -> partita (id, punteggio_raggiunto, id_utente)

// todo domanda (id, valore_domanda) -> risposta (valore_risposta, id_domanda, risposta_corretta)

