<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Beers</h2>
                <table border="1">
                    <tr bgcolor="red">
                        <th>Name</th>
                        <th>Type</th>
                        <th>Alcohol</th>
                        <th>Manufacturer</th>
                        <th>Water</th>
                        <th>Malt</th>
                        <th>Hop</th>
                        <th>Sugar</th>
                        <th>Volume Alcohol Fraction</th>
                        <th>Clearness</th>
                        <th>Filtered</th>
                        <th>Nutritional Value</th>
                        <th>Size</th>
                        <th>Material</th>
                    </tr>
                    <xsl:for-each select="Beers/Beer">
                        <xsl:sort order="descending" select="name"/>
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="type"/>
                            </td>
                            <td>
                                <xsl:value-of select="ai"/>
                            </td>
                            <td>
                                <xsl:value-of select="manufacturer"/>
                            </td>
                            <xsl:for-each select="ingredients">
                                <td>
                                    <xsl:value-of select="water"/>
                                </td>
                                <td>
                                    <xsl:value-of select="malt"/>
                                </td>
                                <td>
                                    <xsl:value-of select="hop"/>
                                </td>
                                <td>
                                    <xsl:value-of select="sugar"/>
                                </td>
                            </xsl:for-each>
                            <xsl:for-each select="chars">
                                <td>
                                    <xsl:value-of select="volumeAlcoholFraction"/>
                                </td>
                                <td>
                                    <xsl:value-of select="clearness"/>
                                </td>
                                <td>
                                    <xsl:value-of select="filtered"/>
                                </td>
                                <td>
                                    <xsl:value-of select="nutritionalValue"/>
                                </td>
                                <xsl:for-each select="spillMethod">
                                    <td>
                                        <xsl:value-of select="size"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="material"/>
                                    </td>
                                </xsl:for-each>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
