package com.example.mohamed.movieapp.DBshema;

/**
 * Created by mohamed on 02/08/2017.
 */

public class DbShema {

    public static class TableFav{
        public static  String NAME="fav";
        public static class CLOS{
            public static String ID="id";
            public static String POSTER="poster";
            public static String TITLE="title";
            public static String RATE="rate";
            public static String RELEASE_DATE="release";
            public static String OVERVIEW="overview";

        }
    }
    public static class TableMOVIE{
        public static  String NAME="movie";
        public static class CLOS{
            public static String ID="id";
            public static String POSTER="poster";
            public static String TITLE="title";
            public static String RATE="rate";
            public static String RELEASE_DATE="release";
            public static String OVERVIEW="overview";

        }
    }

    public static class TableREVIEW{
        public static  String NAME="review";
        public static class CLOS{
            public static String ID="ID";
            public static String AUTHOR="author";
            public static String CONTENT="content";
        }
    }

    public static class TableTrailer{
        public static  String NAME="trailer";
        public static class CLOS{
            public static String ID="ID";
            public static String KEY="key";
            public static String NAME="name";
        }
    }




}
