package by.epam.carsharing.entity;

import java.util.Date;

public class News implements Identifiable {
    private Integer id;
    private Integer userId;
    private String header;
    private String content;
    private Date publicationDate;
    private String imagePath;

    public News() {
    }

    public News(Integer id, Integer userId, String header, String content, Date publicationDate, String imagePath) {
        this.id = id;
        this.userId = userId;
        this.header = header;
        this.content = content;
        this.publicationDate = publicationDate;
        this.imagePath = imagePath;
    }

    @Override
    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != null ? !id.equals(news.id) : news.id != null) return false;
        if (userId != null ? !userId.equals(news.userId) : news.userId != null) return false;
        if (header != null ? !header.equals(news.header) : news.header != null) return false;
        if (content != null ? !content.equals(news.content) : news.content != null) return false;
        if (publicationDate != null ? !publicationDate.equals(news.publicationDate) : news.publicationDate != null)
            return false;
        return imagePath != null ? imagePath.equals(news.imagePath) : news.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", header='").append(header).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", imagePath='").append(imagePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
