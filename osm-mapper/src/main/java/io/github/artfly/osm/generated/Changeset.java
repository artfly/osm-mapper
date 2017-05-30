//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.09 at 04:12:58 PM NOVT 
//


package io.github.artfly.osm.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for changeset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="changeset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://openstreetmap.org/osm/0.6}tag" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" />
 *       &lt;attribute name="user" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" />
 *       &lt;attribute name="created_at" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="closed_at" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="opened" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="minlat" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="minlon" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="maxlat" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="maxlon" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="comments_count" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changeset", propOrder = {
    "tag"
})
public class Changeset {

    protected List<Tag> tag;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger id;
    @XmlAttribute(name = "user")
    protected String user;
    @XmlAttribute(name = "uid")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger uid;
    @XmlAttribute(name = "created_at")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    @XmlAttribute(name = "closed_at")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar closedAt;
    @XmlAttribute(name = "opened")
    protected Boolean opened;
    @XmlAttribute(name = "minlat")
    protected Double minlat;
    @XmlAttribute(name = "minlon")
    protected Double minlon;
    @XmlAttribute(name = "maxlat")
    protected Double maxlat;
    @XmlAttribute(name = "maxlon")
    protected Double maxlon;
    @XmlAttribute(name = "comments_count")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger commentsCount;

    /**
     * Gets the value of the tag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tag }
     * 
     * 
     */
    public List<Tag> getTag() {
        if (tag == null) {
            tag = new ArrayList<Tag>();
        }
        return this.tag;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUid(BigInteger value) {
        this.uid = value;
    }

    /**
     * Gets the value of the createdAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the value of the createdAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedAt(XMLGregorianCalendar value) {
        this.createdAt = value;
    }

    /**
     * Gets the value of the closedAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClosedAt() {
        return closedAt;
    }

    /**
     * Sets the value of the closedAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClosedAt(XMLGregorianCalendar value) {
        this.closedAt = value;
    }

    /**
     * Gets the value of the opened property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOpened() {
        return opened;
    }

    /**
     * Sets the value of the opened property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOpened(Boolean value) {
        this.opened = value;
    }

    /**
     * Gets the value of the minlat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinlat() {
        return minlat;
    }

    /**
     * Sets the value of the minlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinlat(Double value) {
        this.minlat = value;
    }

    /**
     * Gets the value of the minlon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinlon() {
        return minlon;
    }

    /**
     * Sets the value of the minlon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinlon(Double value) {
        this.minlon = value;
    }

    /**
     * Gets the value of the maxlat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxlat() {
        return maxlat;
    }

    /**
     * Sets the value of the maxlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxlat(Double value) {
        this.maxlat = value;
    }

    /**
     * Gets the value of the maxlon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxlon() {
        return maxlon;
    }

    /**
     * Sets the value of the maxlon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxlon(Double value) {
        this.maxlon = value;
    }

    /**
     * Gets the value of the commentsCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCommentsCount() {
        return commentsCount;
    }

    /**
     * Sets the value of the commentsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCommentsCount(BigInteger value) {
        this.commentsCount = value;
    }

}
