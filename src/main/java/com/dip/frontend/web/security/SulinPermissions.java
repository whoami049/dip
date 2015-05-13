package com.sulin.frontend.web.security;

import com.sulin.common.constant.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class SulinPermissions implements Cloneable {
    private final static Logger log = LoggerFactory.getLogger(SulinPermissions.class);
    public static SulinPermissions[] userPermissions;

    private Set<Privilege> privileges;

    static {
        userPermissions = new SulinPermissions[UserType.values().length];
        for(UserType role: UserType.values()){
            userPermissions[role.ordinal()] = new SulinPermissions();
            userPermissions[role.ordinal()].privileges = new HashSet<Privilege>();

            switch (role){
                case SuperAdmin:
                    break;
                case ResponsibleForPayment:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                            .addPrivileges(Privilege.USER_SEARCH)
                            .addPrivileges(Privilege.START)
                            .addPrivileges(Privilege.INDEX);
                    break;
                case SchoolAdmin:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                                                   .addPrivileges(Privilege.USER_EDIT)
                                                   .addPrivileges(Privilege.USER_SEARCH)
                                                   .addPrivileges(Privilege.USER_PASSWORD)
                                                   .addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                    .addPrivileges(Privilege.MARKS_SEARCH);
                    break;
                case Other:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                   .addPrivileges(Privilege.LOGIN)
                                                   .addPrivileges(Privilege.TERMS_LIST)
                                                   .addPrivileges(Privilege.INSTRUCTIONS_LIST)
                                                   .addPrivileges(Privilege.CONTACTS)
                                                   .addPrivileges(Privilege.SERVICES_AMOUNT)
												   .addPrivileges(Privilege.USER_PASSWORD)
                                                   .addPrivileges(Privilege.LOGOUT);
                    break;
                default:
                    break;
            }
        }
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }
    public SulinPermissions addPrivileges(Privilege privilege) {
        if (this.privileges == null)
            this.privileges = new HashSet<Privilege>();
        this.privileges.add(privilege);
        return this;
    }

}
