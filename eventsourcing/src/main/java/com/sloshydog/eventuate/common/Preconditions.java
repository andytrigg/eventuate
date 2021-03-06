package com.sloshydog.eventuate.common;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Preconditions {
    public static String checkArgumentProvided(String value, String name) {
        checkArgument(value != null, "%s is null", name);
        checkArgument(isNotBlank(value), "%s is blank or empty", name);
        return value;
    }

    public static <E>  E checkArgumentProvided(E value, String name) {
        checkArgument(value != null, "%s is null", name);
        return value;
    }
}
