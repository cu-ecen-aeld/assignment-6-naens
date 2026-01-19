# Recipe created by recipetool

SUMMARY = "Linux Device Drivers 3 scull example updated to work in recent kernels"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

SRC_URI = "git://git@github.com/naens/coursea-embedded-assignment7.git;protocol=ssh;branch=master \
           "
SRC_URI += "file://scull_init"

PV = "1.0+git${SRCPV}"
SRCREV = "dd8ba9270cad869bb441091e6b0f6b88e771144e"

S = "${WORKDIR}/git/scull"

inherit module

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install:append() {
        install -d ${D}/etc/init.d/
        install -m 0755 ${WORKDIR}/scull_init ${D}/etc/init.d/scull_init
        install -d ${D}/etc/rcS.d/
        ln -sf ../init.d/scull_init ${D}/etc/rcS.d/S97scull_init
}

FILES:${PN} += "${sysconfdir}/init.d/scull_init ${sysconfdir}/rcS.d/S97scull_init"
