#
# Copyright (C) 2024 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from mondrian device
$(call inherit-product, device/xiaomi/mondrian/device.mk)

# Inherit from common lineage configuration
TARGET_DISABLE_EPPE := true
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

PRODUCT_NAME := lineage_mondrian
PRODUCT_DEVICE := mondrian
PRODUCT_MANUFACTURER := Xiaomi

PRODUCT_SYSTEM_NAME := mondrian_global
PRODUCT_SYSTEM_DEVICE := mondrian

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="mondrian_global-user 12 SKQ1.230401.001 OS2.0.9.0.VMNMIXM release-keys" \
    BuildFingerprint=POCO/mondrian_global/mondrian:12/SKQ1.230401.001/OS2.0.9.0.VMNMIXM:user/release-keys \
    DeviceProduct=$(PRODUCT_DEVICE)

TARGET_BOOT_ANIMATION_RES := 1440

WITH_GMS := true
TARGET_HAS_UDFPS := true
