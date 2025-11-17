package com.openmission.domain;

import java.util.ArrayList;
import java.util.List;

public class DraftMails {
    private List<DraftMail> draftMails;

    public DraftMails() {
        this.draftMails = new ArrayList<>();
    }

    public void add(DraftMail draftMail) {
        this.draftMails.add(draftMail);
    }

    public boolean isEmpty() {
        return this.draftMails.isEmpty();
    }

    public List<DraftMail> getStream() {
        return this.draftMails;
    }

    public DraftMail getMail(Integer index) {
        DraftMail draftMail = this.draftMails.get(index);
        this.draftMails.remove(index);
        System.out.println(getStream().size());
        return draftMail;
    }
}
