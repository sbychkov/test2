TARGETS = console-setup mountkernfs.sh resolvconf ufw hostname.sh apparmor screen-cleanup plymouth-log udev keyboard-setup cryptdisks cryptdisks-early networking urandom iscsid checkroot.sh lvm2 checkfs.sh hwclock.sh open-iscsi mountdevsubfs.sh mountall.sh mountnfs-bootclean.sh mountnfs.sh bootmisc.sh checkroot-bootclean.sh kmod procps mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
urandom: hwclock.sh
iscsid: networking
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
hwclock.sh: mountdevsubfs.sh
open-iscsi: networking iscsid
mountdevsubfs.sh: mountkernfs.sh udev
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
bootmisc.sh: mountnfs-bootclean.sh checkroot-bootclean.sh udev mountall-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
procps: mountkernfs.sh udev
mountall-bootclean.sh: mountall.sh
