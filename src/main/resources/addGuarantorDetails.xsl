<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="Loaner">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
            <GuarantorDetails>
                <Name>Sample Name</Name>
                <INN>1234567890</INN>
            </GuarantorDetails>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
