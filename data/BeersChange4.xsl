<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <changedBeers>
            <xsl:apply-templates/>
        </changedBeers>
    </xsl:template>
    <xsl:template match="Beer">
        <Beer>
            <name>
                <xsl:value-of select="name"/>
            </name>
            <type>
                <xsl:value-of select="type"/>
            </type>
            <ai>
                <xsl:value-of select="ai"/>
            </ai>
            <manufacturer>
                <xsl:value-of select="manufacturer"/>
            </manufacturer>
            <ingredients>
                <water>
                    <xsl:value-of select="ingredients/water"/>
                </water>
                <malt>
                    <xsl:value-of select="ingredients/malt"/>
                </malt>
                <hop>
                    <xsl:value-of select="ingredients/hop"/>
                </hop>
                <sugar>
                    <xsl:value-of select="ingredients/sugar"/>
                </sugar>
            </ingredients>
            <chars>
                <volumeAlcoholFraction>
                    <xsl:value-of select="chars/volumeAlcoholFraction"/>
                </volumeAlcoholFraction>
                <clearness>
                    <xsl:value-of select="chars/clearness"/>
                </clearness>
                <filtered>
                    <xsl:value-of select="chars/filtered"/>
                </filtered>
                <nutritionalValue>
                    <xsl:value-of select="chars/nutritionalValue"/>
                </nutritionalValue>
                <spillMethod>
                    <size>
                        <xsl:value-of select="chars/spillMethod/size"/>
                    </size>
                    <material>
                        <xsl:value-of select="chars/spillMethod/material"/>
                    </material>
                </spillMethod>
            </chars>
        </Beer>
    </xsl:template>
</xsl:stylesheet>