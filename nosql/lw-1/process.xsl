<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html lang="ua">
            <head>
                <meta charset="utf-8"/>
                <title>Лабораторна робота 1</title>
            </head>
            <body>
                <h2>Книгарня</h2>
                <!-- Сортування за автором, фільтрація за роком(після 1980) -->
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th style="text-align:left">Name</th>
                        <th style="text-align:left">author</th>
                        <th style="text-align:left">Year</th>
                    </tr>
                    <xsl:for-each select="papers/*[year>1900]">
                        <xsl:sort select="author"/>
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="author"/>
                            </td>
                            <td>
                                <xsl:value-of select="year"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                <!-- Умова <xsl:if> -->
                <table border="3">
                    <tr bgcolor="#234567">
                        <th style="text-align:left">Name</th>
                        <th style="text-align:left">Genre</th>
                        <th style="text-align:left">author</th>
                    </tr>
                    <xsl:for-each select="papers/comix">
                        <xsl:if test="@genre='психологічний трилер'">
                            <tr>
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="@genre"/>
                                </td>
                                <td>
                                    <xsl:value-of select="author"/>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
                <!-- Умова <xsl:choose> -->
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th style="text-align:left">Name</th>
                        <th style="text-align:left">author</th>
                        <th style="text-align:left">Cost</th>
                    </tr>
                    <xsl:for-each select="papers/comix">
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="author"/>
                            </td>
                            <xsl:choose>
                                <xsl:when test="cost>200">
                                    <td>
                                        <xsl:value-of select="cost"/>
                                    </td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td bgcolor="#54ea13">
                                        <xsl:value-of select="cost"/>
                                    </td>
                                </xsl:otherwise>
                            </xsl:choose>
                        </tr>
                    </xsl:for-each>
                </table>
                <!-- Елемент <xsl:apply-templates> -->
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="papers/*">
        <p>
            <xsl:apply-templates select="name"/>
            <xsl:apply-templates select="author"/>
            <xsl:apply-templates select="acceptance_age"/>
        </p>
    </xsl:template>
    <xsl:template match="name">
        Name:
        <span style="color:#13acd6">
            <xsl:value-of select="."/>
        </span>
        <br/>
    </xsl:template>
    <xsl:template match="author">
        Author:
        <span style="color:#11FF22">
            <xsl:value-of select="."/>
        </span>
        <br/>
    </xsl:template>
    <xsl:template match="acceptance_age">
        Acceptance age:
        <span style="color:#9acd32">
            <xsl:value-of select="."/>+
        </span>
        <br/>
    </xsl:template>
</xsl:stylesheet>