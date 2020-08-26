package com.example.mycart.utils;

import java.io.Serializable;

public class Users  implements Serializable {

        private String name;
        private String City;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getInstitute() {
            return Institute;
        }

        public void setInstitute(String institute) {
            Institute = institute;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        private String Institute;
        private String State;
        private String device_token;

        public boolean ismABoolean_participents() {
            return mABoolean_participents;
        }

        public void setmABoolean_participents(boolean mABoolean_participents) {
            this.mABoolean_participents = mABoolean_participents;
        }

        private boolean mABoolean_participents;

        public String getUid() {
            return Uid;
        }

        public void setUid(String uid) {
            Uid = uid;
        }

        private String Uid;

        public Users(String name, String State, String City,
                     String device_token) {
            this.name = name;
            this.State = State;
            this.City = City;
            this.device_token=device_token;

        }





}
