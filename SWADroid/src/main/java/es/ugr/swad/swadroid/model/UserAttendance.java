/*
 *
 *  *  This file is part of SWADroid.
 *  *
 *  *  Copyright (C) 2010 Juan Miguel Boyero Corral <juanmi1982@gmail.com>
 *  *
 *  *  SWADroid is free software: you can redistribute it and/or modify
 *  *  it under the terms of the GNU General Public License as published by
 *  *  the Free Software Foundation, either version 3 of the License, or
 *  *  (at your option) any later version.
 *  *
 *  *  SWADroid is distributed in the hope that it will be useful,
 *  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *  GNU General Public License for more details.
 *  *
 *  *  You should have received a copy of the GNU General Public License
 *  *  along with SWADroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package es.ugr.swad.swadroid.model;

import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * User attendance data.
 *
 * @author Juan Miguel Boyero Corral <juanmi1982@gmail.com>
 */
public class UserAttendance extends Model {
    /**
     * User identifier.
     */
    private int eventCode;
    /**
     * Flag for indicate if the user is present in the attendance.
     */
    private boolean userPresent;

    private static final PropertyInfo PI_userID = new PropertyInfo();
    private static final PropertyInfo PI_userNickname = new PropertyInfo();
    private static final PropertyInfo PI_userSurname1 = new PropertyInfo();
    private static final PropertyInfo PI_userSurname2 = new PropertyInfo();
    private static final PropertyInfo PI_userFirstname = new PropertyInfo();
    private static final PropertyInfo PI_userPhoto = new PropertyInfo();
    private static final PropertyInfo PI_userPresent = new PropertyInfo();

    @SuppressWarnings("unused")
    private static PropertyInfo[] PI_PROP_ARRAY = {
            PI_userID,
            PI_userNickname,
            PI_userSurname1,
            PI_userSurname2,
            PI_userFirstname,
            PI_userPhoto,
            PI_userPresent
    };

    public UserAttendance(long id) {
        super(id);
    }

    /**
     * Constructor.
     *
     * @param id            User code.
     * @param eventCode     Event code.
     * @param userPresent   Flag for indicate if the user is present in the attendance.
     */
    public UserAttendance(long id, int eventCode, boolean userPresent) {
        super(id);
        this.eventCode = eventCode;
        this.userPresent = userPresent;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public boolean isUserPresent() {
        return userPresent;
    }

    public void setUserPresent(boolean userPresent) {
        this.userPresent = userPresent;
    }

    public Object getProperty(int param) {
        Object object = null;
        switch (param) {
            case 0:
                object = eventCode;
                break;
            case 1:
                object = userPresent;
                break;
        }

        return object;
    }

    public int getPropertyCount() { return PI_PROP_ARRAY.length; }

    public void getPropertyInfo(int param, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo propertyInfo) {
        switch (param) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userID";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userNickname";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userSurname1";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userSurname2";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userFirstname";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userPhoto";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.BOOLEAN_CLASS;
                propertyInfo.name = "userPresent";
                break;
        }
    }

    public void setProperty(int param, Object obj) {
        switch (param) {
            case 0:
                eventCode = (Integer) obj;
                break;
            case 1:
                userPresent = (Boolean) obj;
                break;
        }
    }

    @Override
    public String toString() {
        return "UserAttendance{" +
                "eventCode=" + eventCode +
                ", userPresent=" + userPresent +
                "} " + super.toString();
    }
}
