package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class CmsMail implements Serializable {
    private Integer mailId;

    private String mailInMail;

    private String mailToMail;

    private Date mailSenddate;

    private Date mailReceivedate;

    private String mailContent;

    private Integer mailRead;

    private Integer mailFlagStatus;

    private static final long serialVersionUID = 1L;

    public CmsMail(Integer mailId, String mailInMail, String mailToMail, Date mailSenddate, Date mailReceivedate, String mailContent, Integer mailRead, Integer mailFlagStatus) {
        this.mailId = mailId;
        this.mailInMail = mailInMail;
        this.mailToMail = mailToMail;
        this.mailSenddate = mailSenddate;
        this.mailReceivedate = mailReceivedate;
        this.mailContent = mailContent;
        this.mailRead = mailRead;
        this.mailFlagStatus = mailFlagStatus;
    }

    public CmsMail() {
        super();
    }

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getMailInMail() {
        return mailInMail;
    }

    public void setMailInMail(String mailInMail) {
        this.mailInMail = mailInMail == null ? null : mailInMail.trim();
    }

    public String getMailToMail() {
        return mailToMail;
    }

    public void setMailToMail(String mailToMail) {
        this.mailToMail = mailToMail == null ? null : mailToMail.trim();
    }

    public Date getMailSenddate() {
        return mailSenddate;
    }

    public void setMailSenddate(Date mailSenddate) {
        this.mailSenddate = mailSenddate;
    }

    public Date getMailReceivedate() {
        return mailReceivedate;
    }

    public void setMailReceivedate(Date mailReceivedate) {
        this.mailReceivedate = mailReceivedate;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent == null ? null : mailContent.trim();
    }

    public Integer getMailRead() {
        return mailRead;
    }

    public void setMailRead(Integer mailRead) {
        this.mailRead = mailRead;
    }

    public Integer getMailFlagStatus() {
        return mailFlagStatus;
    }

    public void setMailFlagStatus(Integer mailFlagStatus) {
        this.mailFlagStatus = mailFlagStatus;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CmsMail other = (CmsMail) that;
        return (this.getMailId() == null ? other.getMailId() == null : this.getMailId().equals(other.getMailId()))
            && (this.getMailInMail() == null ? other.getMailInMail() == null : this.getMailInMail().equals(other.getMailInMail()))
            && (this.getMailToMail() == null ? other.getMailToMail() == null : this.getMailToMail().equals(other.getMailToMail()))
            && (this.getMailSenddate() == null ? other.getMailSenddate() == null : this.getMailSenddate().equals(other.getMailSenddate()))
            && (this.getMailReceivedate() == null ? other.getMailReceivedate() == null : this.getMailReceivedate().equals(other.getMailReceivedate()))
            && (this.getMailContent() == null ? other.getMailContent() == null : this.getMailContent().equals(other.getMailContent()))
            && (this.getMailRead() == null ? other.getMailRead() == null : this.getMailRead().equals(other.getMailRead()))
            && (this.getMailFlagStatus() == null ? other.getMailFlagStatus() == null : this.getMailFlagStatus().equals(other.getMailFlagStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMailId() == null) ? 0 : getMailId().hashCode());
        result = prime * result + ((getMailInMail() == null) ? 0 : getMailInMail().hashCode());
        result = prime * result + ((getMailToMail() == null) ? 0 : getMailToMail().hashCode());
        result = prime * result + ((getMailSenddate() == null) ? 0 : getMailSenddate().hashCode());
        result = prime * result + ((getMailReceivedate() == null) ? 0 : getMailReceivedate().hashCode());
        result = prime * result + ((getMailContent() == null) ? 0 : getMailContent().hashCode());
        result = prime * result + ((getMailRead() == null) ? 0 : getMailRead().hashCode());
        result = prime * result + ((getMailFlagStatus() == null) ? 0 : getMailFlagStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mailId=").append(mailId);
        sb.append(", mailInMail=").append(mailInMail);
        sb.append(", mailToMail=").append(mailToMail);
        sb.append(", mailSenddate=").append(mailSenddate);
        sb.append(", mailReceivedate=").append(mailReceivedate);
        sb.append(", mailContent=").append(mailContent);
        sb.append(", mailRead=").append(mailRead);
        sb.append(", mailFlagStatus=").append(mailFlagStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}