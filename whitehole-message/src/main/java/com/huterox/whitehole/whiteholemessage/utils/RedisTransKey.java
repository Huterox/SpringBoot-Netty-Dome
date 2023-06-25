package com.huterox.whitehole.whiteholemessage.utils;

public class RedisTransKey {

    public static final String RedisNameSpace="user";
    public static final String RedisTokenName="token";
    public static final String RedisLoginName="login";
    public static final String RedisNameSpaceRoot="message";
    public static final String RedisAnsThumbLoad = "ansThumbLoad";
    public static final String RedisBlogCommentLoad = "blogCommentLoad";
    public static final String RedisBlogPullLoad = "blogPullLoad";
    public static final String RedisBlogThumbLoad = "blogThumbLoad";
    public static final String RedisAduitLoad = "aduitLoad";
    public static final String RedisAnsCommentLoad = "ansCommentLoad";
    public static final String RedisAnsTipsLoad = "ansTipsLoad";


    public static String setRootKey(String key){
        return RedisNameSpaceRoot+":"+key+":";
    }
    public static String setTokenKey(String key){
        return RedisNameSpace+':'+RedisTokenName+":"+key;
    }
    public static String setLoginKey(String key){
        return RedisNameSpace+':'+RedisLoginName+":"+key;
    }

    public static String setAnsThumbLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisAnsThumbLoad+":"+key;
    }
    public static String setBlogCommentLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisBlogCommentLoad+":"+key;
    }
    public static String setBlogPullLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisBlogPullLoad+":"+key;
    }
    public static String setBlogThumbLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisBlogThumbLoad+":"+key;
    }
    public static String setAduitLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisAduitLoad+":"+key;
    }
    public static String setAnsCommentLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisAnsCommentLoad+":"+key;
    }
    public static String setAnsTipsLoadKey(String key){
        return RedisNameSpaceRoot+':'+RedisAnsTipsLoad+":"+key;
    }


    public static String getRootKey(String key){return setRootKey(key);}
    public static String getTokenKey(String key){return setTokenKey(key);}
    public static String getLoginKey(String key){return setLoginKey(key);}

    public static String getAnsThumbLoadKey(String key){
        return setAnsThumbLoadKey(key);
    }
    public static String getBlogCommentLoadKey(String key){
        return setBlogCommentLoadKey(key);
    }
    public static String getBlogPullLoadKey(String key){
        return setBlogPullLoadKey(key);
    }
    public static String getBlogThumbLoadKey(String key){
        return setBlogThumbLoadKey(key);
    }
    public static String getAduitLoadKey(String key){
        return setAduitLoadKey(key);
    }
    public static String getAnsCommentLoadKey(String key){
        return setAnsCommentLoadKey(key);
    }
    public static String getAnsTipsLoadKey(String key){
        return setAnsTipsLoadKey(key);
    }


}
