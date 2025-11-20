package com.openmission.util;

import com.openmission.domain.Receiver;
import com.openmission.view.InputView;
import com.openmission.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class Utils {
    public static void retryUntilGet(String condition, Supplier<String> supplier) {
        while (true) {
            String result = supplier.get();
            if (result.equalsIgnoreCase(condition)) {
                break;
            }
        }
    }

    public static <T> T get(ThrowingSupplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static List<Receiver> enterReceivers(List<Receiver> receivers, Supplier<String> supplier) {
        while (true) {
            try {
                String input = supplier.get();
                Receiver receiver = Receiver.from(input);
                receivers.add(receiver);
            } catch (Exception e) {
                break;
            }
        }
        if (receivers.isEmpty()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
        return receivers;
    }

    public static <T, U, R> void accept(TriConsumer<T, U, R> consumer, T t, U u, R r) {
        while (true) {
            try {
                consumer.accept(t, u, r);
                return ;
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
