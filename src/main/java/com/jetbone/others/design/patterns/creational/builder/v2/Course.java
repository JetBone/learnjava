package com.jetbone.others.design.patterns.creational.builder.v2;

/**
 * Created by Chris on 2019-08-11 10:04.
 */
public class Course {

    private String name;
    private String video;
    private String article;

    public Course(CourseBuilder courseBuilder) {
        this.name = courseBuilder.name;
        this.video = courseBuilder.video;
        this.article = courseBuilder.article;
    }

    public static class CourseBuilder {
        private String name;
        private String video;
        private String article;

        public CourseBuilder buildName(String name) {
            this.name = name;

            return this;
        }

        public CourseBuilder buildVideo(String video) {
            this.video = video;

            return this;
        }

        public CourseBuilder buildArticle(String article) {
            this.article = article;

            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
