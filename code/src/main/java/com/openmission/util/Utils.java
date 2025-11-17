package com.openmission.util;

import com.openmission.View.OutputView;
import com.openmission.domain.DraftMail;
import com.openmission.domain.DraftMails;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Utils {
    public static  <T> T getUntilValid(Supplier<T> supplier) {
        return getUntilValid(supplier, null);
    }

    public static <T, R> R runUntilValid(Function<T, R> function, T t) {
        while (true) {
            try {
                return (R) function.apply(t);
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static  <T> T getUntilValid(Supplier<T> supplier, String condition) {
        while (true) {
            try {
                T t = supplier.get();
                if (t.equals(condition)) {
                    continue;
                }
                return t;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public static <T, U, R> void runUntilValid(TriConsumer<T, U, R> consumer, T t, U u, R r) {
        while (true) {
            try {
                consumer.accept(t, u, r);
                return ;
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static String wantDraft(String input) {
        if (input.equals("STOP")) {
            throw new RuntimeException("임시 저장합니다.");
        }
        return input;
    }

    public static void eachMails(DraftMails draftMails, BiConsumer<DraftMail, Integer> display) {
        eachMails(draftMails, display, null);
    }

    public static void eachMails(DraftMails draftMails, BiConsumer<DraftMail, Integer> display, Integer pickNumber) {
        int i = 1;
        for (DraftMail mail : draftMails.getStream()) {
            if (pickNumber == null || i == pickNumber) {
                display.accept(mail, i);
            }
            i++;
        }
    }
}
