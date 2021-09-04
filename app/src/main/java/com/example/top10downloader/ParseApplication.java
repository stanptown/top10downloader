package com.example.top10downloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseApplication {
    private static final String TAG = "ParseApplication";
    private ArrayList<FeedEntry> applications;

    public ParseApplication() {
        this.applications=new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    public boolean parse (String xmlData){
        boolean status=true;
        FeedEntry currentRecord=null;//to make android studio happy
        boolean inEntry=false;
        String textValue="";

        try{
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp=factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType=xpp.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String tagName=xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
//                        Log.d(TAG, "parse: Starting tag for "+ tagName);
                        if ("entry".equalsIgnoreCase(tagName)){    //tagname.equalsIgnoreCase("entry") NOT Used for null
                            inEntry=true;
                            currentRecord=new FeedEntry();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue=xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
//                        Log.d(TAG, "parse: Ending tag for "+tagName);
                        if (inEntry){
                            if ("entry".equalsIgnoreCase(tagName)){
                                applications.add(currentRecord);
                                inEntry=false;
                            }
                            else if ("name".equalsIgnoreCase(tagName)){
                                currentRecord.setName(textValue);

                            }
                            else if ("artist".equalsIgnoreCase(tagName)){
                                currentRecord.setArtist(textValue);
                            }
                            else if ("releaseData".equalsIgnoreCase(tagName)){
                                currentRecord.setReleaseDate(textValue);
                            }
                            else if ("summary".equalsIgnoreCase(tagName)){
                                currentRecord.setSummary(textValue);
                            }
                            else if ("image".equalsIgnoreCase(tagName)){
                                currentRecord.setImageURl(textValue);
                            }
                        }
                        break;
                    default:
                        //nothing
                }
                eventType=xpp.next();
                
            }

        } catch(Exception e){
            status=false;
            e.printStackTrace();
        }
        return status;
        

    }
}
