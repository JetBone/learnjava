package com.jetbone.others.design.patterns.creational.simplefactory;

/**
 * Created by Chris on 2019-08-01 22:54.
 */
public class VideoFactory {

    public Video getVideo(String type) {
        if (type.equalsIgnoreCase("java")) {
            return new JavaVideo();
        } else if (type.equalsIgnoreCase("python")) {
            return new PythonVideo();
        } else {
            return null;
        }
    }

    public Video getVideo(Class< ? extends Video> type) {
        Video video = null;
        try {
            video = (Video) Class.forName(type.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }
}
