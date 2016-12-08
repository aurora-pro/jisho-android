package com.example.maryam.jishoorg;

import android.os.Parcel;
import android.os.Parcelable;

class Pair implements Parcelable {
        private String word = null;
        private String reading = null;

        Pair (String word, String reading){
            this.word = word;
            this.reading = reading;
        }

        public static final Creator<Pair> CREATOR = new Creator<Pair>() {
            @Override
            public Pair createFromParcel(Parcel in) {
                return new Pair(in);
            }

            @Override
            public Pair[] newArray(int size) {
                return new Pair[size];
            }
        };

        public void setWord (String word){
            this.word = word;
        }
        public void setReading (String reading){
            this.reading = reading;
        }
        public String getWord (){
            return word;
        }
        public String getReading (){
            return reading;
        }

        @Override
        public int describeContents() {
            return 0;
        }



        @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(word);
                parcel.writeString(reading);
            }
            private Pair (Parcel parcel){
                word = parcel.readString();
                reading = parcel.readString();
            }

            public final Parcelable.Creator<Pair> creator = new Creator<Pair>() {
                @Override
                public Pair createFromParcel(Parcel parcel) {
                    return new Pair(parcel);
                }

                @Override
                public Pair[] newArray(int i) {
                    return new Pair[i];
                }
            };
    }